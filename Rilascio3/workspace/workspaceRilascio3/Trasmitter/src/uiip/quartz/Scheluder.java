package uiip.quartz;

import static org.quartz.JobBuilder.newJob;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.Trigger;

import uiip.job.Trasmitter;

public class Scheluder {
	public Scheluder() throws Exception {

		SchedulerFactory sf = new StdSchedulerFactory();

		Scheduler sche = sf.getScheduler();

		sche.start();

		JobDetail jDetail = newJob(Trasmitter.class).withIdentity("jobReceiver", "groupReceiver").build();

		//"0 0 12 * * ?" Fire at 12pm (noon) every day
		//"0/2 * * * * ?" Fire at every 2 seconds every day

		Trigger crTrigger = newTrigger()
        .withIdentity("trigger1", "group1")
        .startNow()
        .withSchedule(simpleSchedule()
                .withIntervalInSeconds(5)
                .repeatForever())            
        .build();
		sche.scheduleJob(jDetail, crTrigger);
	}
}
