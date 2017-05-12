package kolekcje_i_algorytmy;

import java.io.Serializable;
import java.util.List;

public interface ExtractInterface extends Serializable {
	public void handled(List<Student> list, OrderMode mode);
}
