package controller;

import dao.EventCategoryDaoSQLite;
import dao.EventDao;
import dao.EventDaoSQLite;
import model.Event;
import model.EventCategory;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class EventController {

    public ModelAndView renderEvents(String choice, int id, Request req, Response res) {
        EventDaoSQLite eventDaoSQLite = new EventDaoSQLite();
        EventCategoryDaoSQLite eventCategoryDaoSQLite = new EventCategoryDaoSQLite();
        Map params = new HashMap<>();
        if(choice.equals("")){
            params.put("events", eventDaoSQLite.getAll());
        } else if(choice.equals("EventCategory")) {
            params.put("events", eventDaoSQLite.getBy(id));
        }
        params.put("eventCategory", eventCategoryDaoSQLite.getAll());
        return new ModelAndView(params, "event/index");
    }
    public void removeEvents(int id){
        EventDaoSQLite eventDaoSQLite = new EventDaoSQLite();
        eventDaoSQLite.remove(id);
    }
    public void addEvents(Event event){
        EventDaoSQLite eventDaoSQLite = new EventDaoSQLite();
        eventDaoSQLite.add(event);
    }

    public void editEvents(int id, String name, Integer eventCategoryId, Date date, String description){
        EventDaoSQLite eventDaoSQLite = new EventDaoSQLite();
        Event event = eventDaoSQLite.find(id);
        EventCategoryDaoSQLite eventCategoryDaoSQLite = new EventCategoryDaoSQLite();
        int categoryId = event.getEventCategory().getId();
        EventCategory eventCategory = eventCategoryDaoSQLite.find(categoryId);
        event.setName(name);
        event.setDate(date);
        event.setEventCategory(eventCategory);
        event.setDescription(description);
        eventDaoSQLite.editEvent(event);
    }
}
