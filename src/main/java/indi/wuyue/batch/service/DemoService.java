package indi.wuyue.batch.service;

import indi.wuyue.batch.bean.Person;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface DemoService {

    void testExport(HttpServletResponse response);

    List<Person> testPeopleQuery();

    void testPeopleExport(HttpServletResponse response);

}
