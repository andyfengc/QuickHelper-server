package smart.quickhelper.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Thread {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long Id;

}
