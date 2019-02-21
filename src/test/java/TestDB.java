import com.pandaear.handler.DataHandler;
import com.pandaear.handler.TableHandler;
import com.pandaear.model.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: pandaear
 * @create: 2019-02-19 14:50
 **/
public class TestDB {
	public static void main(String[] args) {
		TableHandler usersHandler = new TableHandler("users");
		DataHandler<User> userDataHandler = new DataHandler<>();
		ArrayList<User> list = new ArrayList<>();

		for(long i = 0;i<1000000;i++){
			User user = new User(i, "小李", 17);
			list.add(user);
		}
		userDataHandler.saveList(list,usersHandler);
	}
	@Test
	public void testSelectAll(){
		TableHandler usersHandler = new TableHandler("users");
		DataHandler<User> userDataHandler = new DataHandler<>();
		List<User> users = userDataHandler.selectAll(usersHandler);
		System.out.println(users);
	}
	@Test
	public void testDelete(){
		TableHandler usersHandler = new TableHandler("users");
		DataHandler<User> userDataHandler = new DataHandler<>();
		Map<String, Object> params = new HashMap<>();
		params.put("name","小李");
		params.put("age",17);
		userDataHandler.deleteData(params,usersHandler);
	}
	@Test
	public void testSelectByParams(){
		TableHandler usersHandler = new TableHandler("users");
		DataHandler<User> userDataHandler = new DataHandler<>();
		Map<String, Object> params = new HashMap<>();
		//params.put("name","小李");
		//params.put("age",17);
		params.put("id",1);
		System.out.println(userDataHandler.selectByParams(params, usersHandler));
	}
}
