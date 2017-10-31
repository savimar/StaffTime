# StaffTime
«Учет рабочего времени сотрудников» (упрощенный) 

Используемые технологии:
Vaadin, JDBC, PostgreSql, Maven, JUnit, Log4j, Poi(выгрузка и загрузка Excel)


Разработать простую веб-форму (можно использовать любую технологию)
На странице только 3 кнопки "пришел", "ушел" и "отработал" 

Сотрудник заходить на сайт и жмет "пришел" (это событие отправляется на веб-сервер (java) и фиксируется, что сотрудник пришел на работу во столько-то, статус "на работе".

В течении дня сотрудник может отходить «покурить», тогда он должен кликнуть на кнопке "ушел" и его статус меняется на "отсутствует" . После возвращения, нажимает снова кнопку «пришел» и т.д.

При нажатии на кнопку "Отработал" должен выгрузиться отчет Excel (из веб-сервера забирается расчетное время, сколько сотрудник отработал, с учетом нажатий кнопок "ушел" и "пришел".)


"Accounting of employees' working hours" (simplified)

Used technologies: Vaadin, JDBC, PostgreSql, Maven, JUnit, Log4j, Poi (upload and download Excel)

Develop a simple web form (you can use any technology) On the page, only 3 buttons "came", "left" and "worked"

Employee go to the site and press "came" (this event is sent to the web server (java) and it is fixed that the employee came to work in so many, the status "at work."

During the day, the employee can go "smoke", then he should click on the "left" button and his status changes to "absent". After returning, presses the "come" button again, etc.

When you click on the "Worked" button, the Excel report should be unloaded (from the web server the estimated time is calculated, how many employees worked, taking into account the buttons "left" and "came.")
