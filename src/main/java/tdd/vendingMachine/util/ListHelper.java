package tdd.vendingMachine.util;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Robert on 2015-07-18.
 */
public class ListHelper<T>
{
    public List<T> copyDeep(List<T> list)
    {
	return list.stream().collect(Collectors.toList());
    }
}
