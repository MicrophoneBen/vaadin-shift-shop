package com.lt.css.shiftShp.view;

import com.lt.css.shiftShp.entity.ShiftTimeDto;
import com.lt.css.shiftShp.entity.item.ShiftItem;
import com.lt.css.shiftShp.service.DgAndDpFromGrid;
import com.lt.css.shiftShp.service.ShiftDataProvider;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Component;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.addon.calendar.Calendar;
import org.vaadin.addon.calendar.handler.BasicDateClickHandler;
import org.vaadin.addon.calendar.ui.CalendarComponentEvents;

import javax.annotation.PostConstruct;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * @program: ShiftCalendarView
 * @description: 日历表的View层
 * @author: ben.zhang.b.q
 * @create: 2019-01-29 20:43
 **/
@UIScope
@SpringView(name = ShiftCalendarView.VIEW_NAME)
public class ShiftCalendarView extends VerticalLayout implements View {
    public static final String VIEW_NAME = "shiftcalendar";
    private final static Logger logger = LoggerFactory.getLogger(ShiftCalendarView.class);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd:HH:mm:ss");

    //事件数据提供类
    private ShiftDataProvider eventProvider = new ShiftDataProvider();

    //一个日历表,显示，包含移动班次事件
    private Calendar<ShiftItem> calendar = new Calendar<>(eventProvider);

    @Autowired
    private DgAndDpFromGrid dgAndDpFromGrid;

    @PostConstruct
    public void init() {
//        setId("调班时间");
        setSizeFull();

        //页面布局管理器
        setMargin(true);
        setSpacing(true);
        setSizeFull();

        //把Panel放进布局管理器
        addComponent(initCalendar());
    }

    //跳转到月份版面
    public void switchToMonth(Month month) {
        calendar.withMonth(month);
    }

    //返回一个时间类型的自定义事件
    public Calendar<ShiftItem> getCalendar() {
        return calendar;
    }

    //事件安排,事件的时间范围设置
    private void onCalendarRangeSelect(CalendarComponentEvents.RangeSelectEvent event) {

        //开始时间不等于结束时间，则创建一个事件,这里是创建事件时间入口
        ShiftTimeDto shiftTimeDto = new ShiftTimeDto(!event.getStart().
                truncatedTo(DAYS).equals(event.getEnd().truncatedTo(DAYS)));

        logger.info("调动员工班次");
        //页面设置事件的开始时间与结束时间
        shiftTimeDto.setStart(event.getStart());
        //logger.info(String.valueOf(event.getStart()));
        logger.info(event.getStart().format(formatter));
        shiftTimeDto.setEnd(event.getEnd());
        //logger.info(String.valueOf(event.getEnd()));
        logger.info(event.getEnd().format(formatter));

//        shiftTimeDto.setName("A Name");
//        shiftTimeDto.setDetails("A Detail<br>with HTML<br> with more lines");


        //做一个随机种子，用来指定添加的事件是一个计划事件还是一个确认事件,设置事件类型
//        shiftTimeDto.setState(R.nextInt(2) == 1 ? ShiftTimeDto.State.planned : ShiftTimeDto.State.confirmed);
//        shiftTimeDto.setState(R.nextInt(2) == 1 ? ShiftTimeDto.State.planned : ShiftTimeDto.State.confirmed);
        shiftTimeDto.setState(ShiftTimeDto.State.planned);

//        meeting.setState(R.nextInt(2) == 1 ? Meeting.State.planned : Meeting.State.confirmed);

        //创建一个事件提供对象，加到事件中
        eventProvider.addItem(new ShiftItem(shiftTimeDto));

        //设置时间的详细信息
        shiftTimeDto.setName("员工临时调班");
        //shiftTimeDto.setDetails("详细信息 ：\n 调动从 \n" + event.getStart() + "\n 到 \n" + event.getEnd() + "\n 为止");
        shiftTimeDto.setDetails("员工调班事件" + "<br/>" + event.getStart().format(formatter) + "<br/>" + event.getEnd().format(formatter));
        shiftTimeDto.setDescription(dgAndDpFromGrid.showShiftEmp()  + "临时调班\n" + event.getStart().format(formatter) +
                "\n" + event.getEnd().format(formatter));
    }

    //获取鼠标点击日期表的事件来触发触发事件
    private void onCalendarClick(CalendarComponentEvents.ItemClickEvent event) {
        ShiftItem item = (ShiftItem) event.getCalendarItem();

        //获取这个事件，显示详细信息
        final ShiftTimeDto shiftTimeDto = item.getShiftDto();
        Notification.show(shiftTimeDto.getName(), shiftTimeDto.getDescription(), Notification.Type.TRAY_NOTIFICATION);

    }
//     //确认消息对话框
//        ConfirmDialog.show(UI.getCurrent(), "确认调动:", "是否确认调动该员工的班次?",
//                "确认", "取消", new ConfirmDialog.Listener() {
//                    @Override
//                    public void onClose(ConfirmDialog dialog) {
//                        if (dialog.isConfirmed()) {
//                            // Confirmed to continue
//                            Notification.show("已确认");
//                        } else {
//                            // User did not confirm
//                            Notification.show("已取消");
//                        }
//                    }
//                });

    //初始化一个日历表
    public Component initCalendar() {
        //新建一个Panel 用来放置Calendar
        Panel panel = new Panel(calendar);

        //新建一个Panel放置Calendar
        panel.setHeight(100, Unit.PERCENTAGE);

        calendar.addStyleName("shiftings");
        calendar.setWidth(100.0f, Unit.PERCENTAGE);
        calendar.setHeight(100.0f, Unit.PERCENTAGE);
        calendar.setResponsive(true);

        calendar.setItemCaptionAsHtml(true);
        calendar.setContentMode(ContentMode.HTML);
        calendar.setWidth(100.0f, Unit.PERCENTAGE);
        calendar.setHeight(100.0f, Unit.PERCENTAGE);
        calendar.setResponsive(true);

        addShiftingItem(LocalDateTime.of(2019, 2, 2, 9, 40),
                LocalDateTime.of(2019, 2, 2, 18, 0));

        addShiftingItem(LocalDateTime.of(2019, 2, 2, 11, 40),
                LocalDateTime.of(2019, 2, 2, 15, 0));

        addOffingItem(LocalDate.of(2019, 2, 7),
                LocalTime.of(8, 00),
                LocalDate.of(2019, 2, 7),
                LocalTime.of(18, 00));

        addOffingItem(LocalDate.of(2019, 2, 8),
                LocalTime.of(8, 00),
                LocalDate.of(2019, 2, 8),
                LocalTime.of(18, 00));

        addOffingItem(LocalDate.of(2019, 2, 9),
                LocalTime.of(8, 00),
                LocalDateTime.of(2019, 2, 9, 18, 30));

//        calendar.setStartDate(ZonedDateTime.now());
        calendar.withWeek(ZonedDateTime.now());

        calendar.setLocale(Locale.CHINA);
        calendar.setZoneId(ZoneId.of("Asia/Shanghai"));
        calendar.setWeeklyCaptionProvider(date -> "<br>" + DateTimeFormatter.ofPattern("dd.MM.YYYY", getLocale()).format(date));
        calendar.setWeeklyCaptionProvider(date -> DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).withLocale(getLocale()).format(date));

        //设置日历表的显示格式
        calendar.withVisibleDays(1, 7);

        //设置月份显示
        //calendar.withMonth(ZonedDateTime.now().getMonth());
        addCalendarEventListeners();

        return panel;
    }

    private void addCalendarEventListeners() {
        calendar.setHandler(new BasicDateClickHandler(true));
        calendar.setHandler(this::onCalendarClick);
        calendar.setHandler(this::onCalendarRangeSelect);
        calendar.setHandler(this::moveItemEvent);
    }

    private void addShiftingItem(LocalDateTime startDateTime, LocalDateTime endDateTime){
        ZoneId zoneId = ZoneId.of("Asia/Shanghai");
        //创建一个日历表项目
        ShiftTimeDto shiftTime = new ShiftTimeDto(false);
        //是同一天则添加一个项目 MeetingItem
        ZonedDateTime startTime = ZonedDateTime.of(startDateTime, zoneId);
        ZonedDateTime endTime = ZonedDateTime.of(endDateTime, zoneId);

        shiftTime.setStart(startTime);
        shiftTime.setEnd(endTime);

        shiftTime.setName("员工临时调班");
        //生成一个便签式提示信息
        shiftTime.setDetails("员工调班事件" + "<br/>" + shiftTime.getStart().format(formatter) + "<br/>" + shiftTime.getEnd().format(formatter));

        //Notification 显示信息
        shiftTime.setDescription(dgAndDpFromGrid.showShiftEmp() + "\n 临时调班\n" + shiftTime.getStart().format(formatter) + "\n" + shiftTime.getEnd().format(formatter));

        shiftTime.setState(ShiftTimeDto.State.confirmed);

        eventProvider.addItem(new ShiftItem(shiftTime));
    }


    //重载一个CalendarItem添加方法
    private void addOffingItem(LocalDate startDate, LocalTime saTime, LocalDate endDate, LocalTime enTime){
        ZoneId zoneId = ZoneId.of("Asia/Shanghai");
        //创建一个日历表项目
        ShiftTimeDto shiftTime = new ShiftTimeDto(false);
        //是同一天则添加一个项目 MeetingItem
        ZonedDateTime startTime = ZonedDateTime.of(startDate, saTime,zoneId);
        ZonedDateTime endTime = ZonedDateTime.of(endDate, enTime, zoneId);

        shiftTime.setStart(startTime);
        shiftTime.setEnd(endTime);

        shiftTime.setName("员工临时调班");
        //生成一个便签式提示信息
        shiftTime.setDetails("员工调班事件" + "<br/>" + shiftTime.getStart().format(formatter) +
                "<br/>" + shiftTime.getEnd().format(formatter));

        //Notification 显示信息
        shiftTime.setDescription(dgAndDpFromGrid.showShiftEmp() + "\n 临时调班\n" +
                shiftTime.getStart().format(formatter) + "\n" + shiftTime.getEnd().format(formatter));

        //设置成请假OFF事件
        shiftTime.setState(ShiftTimeDto.State.dayoff);

        eventProvider.addItem(new ShiftItem(shiftTime));
    }

    //重载一个CalendarItem添加方法
    private void addOffingItem(LocalDate startDate, LocalTime saTime, LocalDateTime endDateTime){
        ZoneId zoneId = ZoneId.of("Asia/Shanghai");
        //创建一个日历表项目,如果设置成true就默认没有显示长度宽度
        ShiftTimeDto shiftTime = new ShiftTimeDto(false);
        //是同一天则添加一个项目 MeetingItem
        ZonedDateTime startTime = ZonedDateTime.of(startDate, saTime, zoneId);
        ZonedDateTime endTime = ZonedDateTime.of(endDateTime, zoneId);

        shiftTime.setStart(startTime);
        shiftTime.setEnd(endTime);

        shiftTime.setName("员工临时调班");
        //生成一个便签式提示信息
        shiftTime.setDetails("员工调班事件" + "<br/>" + shiftTime.getStart().format(formatter)
                + "<br/>" + shiftTime.getEnd().format(formatter));

        //Notification 显示信息
        shiftTime.setDescription(dgAndDpFromGrid.showShiftEmp() + "\n 临时调班\n"
                + shiftTime.getStart().format(formatter) + "\n" + shiftTime.getEnd().format(formatter));

        //设置成请假OFF事件
        shiftTime.setState(ShiftTimeDto.State.origin);

        eventProvider.addItem(new ShiftItem(shiftTime));
    }

    private int getMonthInt(Month month){
        int monthInt = 0;
        switch (month) {
            case JANUARY:
                monthInt = 1;
                break;
            case FEBRUARY:
                monthInt = 2;
                break;
            case MARCH:
                monthInt = 3;
                break;
            case APRIL:
                monthInt = 4;
                break;
            case MAY:
                monthInt = 5;
                break;
            case JUNE:
                monthInt = 6;
                break;
            case JULY:
                monthInt = 7;
                break;
            case AUGUST:
                monthInt = 8;
                break;
            case SEPTEMBER:
                monthInt = 9;
                break;
            case OCTOBER:
                monthInt = 10;
                break;
            case NOVEMBER:
                monthInt = 11;
                break;
            case DECEMBER:
                monthInt = 12;
                break;
            default:
                System.out.println("月份处理错误");
                throw new RuntimeException();
        }
        return  monthInt;
    }

    private  void moveItemEvent(CalendarComponentEvents.ItemMoveEvent itemMoveEvent){
        ShiftItem moveShifting = (ShiftItem) itemMoveEvent.getCalendarItem();

        ZonedDateTime moveShiftingStart = moveShifting.getStart();
        ZonedDateTime moveShiftingEnd = moveShifting.getEnd();
        logger.info("移动前时间 》》》》》》》》》");
        logger.info(moveShiftingStart.format(formatter));
        logger.info(moveShiftingEnd.format(formatter));

        logger.info("移动后时间 》》》》》》》》");
        ZonedDateTime newStart = itemMoveEvent.getNewStart();
        logger.info(newStart.format(formatter));
        int hour = moveShiftingEnd.getHour() - moveShiftingStart.getHour();
        int minute = moveShiftingEnd.getMinute() - moveShiftingStart.getMinute();
        newStart = newStart.plusHours(hour);
        newStart = newStart.plusMinutes(minute);
        logger.info(newStart.format(formatter));

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
}
