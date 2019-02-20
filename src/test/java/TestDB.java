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
		User user1 = new User(1L, "小李", 17);
		User user2 = new User(2L, "小飞", 18);
		User user3 = new User(3L, "小月", 19);
		DataHandler<User> userDataHandler = new DataHandler<>();
		ArrayList<User> list = new ArrayList<>();
		for(int i = 0;i<10;i++){
			list.add(user1);
			list.add(user2);
			list.add(user3);
		}
		userDataHandler.saveData(user2,usersHandler);
		userDataHandler.saveData(user3,usersHandler);
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
		params.put("name","小飞");
		//params.put("age",17);
		System.out.println(userDataHandler.selectByParams(params, usersHandler));
	}
}
