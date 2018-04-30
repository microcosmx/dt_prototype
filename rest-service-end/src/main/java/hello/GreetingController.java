package hello;

import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="cal", defaultValue="50") String cal) throws Exception {
    	// log.info(cal);

    	double cal2 = Math.abs(Double.valueOf(cal)-50); 
    	log.info(String.valueOf(cal2));
        
    	Greeting value = null;
        if(cal2 < 6){
        	throw new Exception("unexpected small input");
        }else if(cal2 < 100){
        	value = new Greeting(counter.incrementAndGet(), Double.valueOf(cal2)<100);
        }else{
        	throw new Exception("unexpected input scope");
        }
        
        log.info("--------service end-----------");
        log.info(value.toString());
        return value;
    }
    
    @RequestMapping("/test")
    public String test(@RequestParam(value="cal", defaultValue="50") String cal) throws Exception {
    	// log.info(cal);

    	double cal2 = Math.abs(Double.valueOf(cal)-50); 
    	
        return String.valueOf(cal2);
    }
}
