package Game;

public class CrushCheck {
	public boolean check;

	public CrushCheck(int x, int y ,int w , int h,  int a , int b , int ww , int hh) {
		
		check=false;
		//유저의 1사분면 꼭짓점
		int user_1_x=x+w;
		int user_1_y=y;
		//유저의 2사분면 꼭짓점
		int user_2_x=x;
		int user_2_y=y;
		//유저의 3사분면 꼭짓점
		int user_3_x=x;
		int user_3_y=y+h;
		//유저의 4사분면 꼭짓점
		int user_4_x=x+w;
		int user_4_y=y+h;
		
		int enemy_1_x=a+ww;
		int enemy_1_y=b;
		
		int enemy_2_x=a;
		int enemy_2_y=b;
		
		int enemy_3_x=a;
		int enemy_3_y=a+hh;
		
		int enemy_4_x=a+ww;
		int enemy_4_y=a+hh;
		
		//1 n 3
		if( (user_1_x >= enemy_3_x) && (user_2_x <= enemy_3_x) && (user_1_y <= enemy_3_y) && (user_4_y >= enemy_3_y) ) {
			//System.out.println("1 n 3 " + user_1_x + "|"+ user_1_y + "|" + user_2_x + "|" + user_2_y + "|" + user_3_x + "|" + user_3_y + "|" + user_4_x + "|" + user_4_y);
			//System.out.println("1 n 3 " + enemy_1_x + "|"+ enemy_1_y + "|" + enemy_2_x + "|" + enemy_2_y + "|" + enemy_3_x + "|" + enemy_3_y + "|" + enemy_4_x + "|" + enemy_4_y);
			check=true;
		}
		//2 n 4
		else if ( (user_2_x <= enemy_4_x) && (user_1_x >= enemy_4_x) && (user_2_y <= enemy_4_y) && (user_3_y >= enemy_4_y) ) {
			//System.out.println("2 n 4 " + user_1_x + "|"+ user_1_y + "|" + user_2_x + "|" + user_2_y + "|" + user_3_x + "|" + user_3_y + "|" + user_4_x + "|" + user_4_y);
			//System.out.println("2 n 4 " + enemy_1_x + "|"+ enemy_1_y + "|" + enemy_2_x + "|" + enemy_2_y + "|" + enemy_3_x + "|" + enemy_3_y + "|" + enemy_4_x + "|" + enemy_4_y);
			check=true;
		}
		//4 n 2
		else if ( (user_4_x >= enemy_2_x) && (user_3_x <= enemy_2_x) && (user_4_y >= enemy_2_y) && (user_1_y <= enemy_2_y) ) {
			//System.out.println("4 n 2 " + user_1_x + "|"+ user_1_y + "|" + user_2_x + "|" + user_2_y + "|" + user_3_x + "|" + user_3_y + "|" + user_4_x + "|" + user_4_y);
			//System.out.println("4 n 2 " + enemy_1_x + "|"+ enemy_1_y + "|" + enemy_2_x + "|" + enemy_2_y + "|" + enemy_3_x + "|" + enemy_3_y + "|" + enemy_4_x + "|" + enemy_4_y);
			check=true;
		}
		//3 n 1
		else if( (user_3_x <= enemy_1_x) && (user_4_x >= enemy_1_x) && (user_3_y >= enemy_1_y) && (user_2_y <= enemy_1_y)) {
			//System.out.println("3 n 1 " + user_1_x + "|"+ user_1_y + "|" + user_2_x + "|" + user_2_y + "|" + user_3_x + "|" + user_3_y + "|" + user_4_x + "|" + user_4_y);
			//System.out.println("3 n 1 " + enemy_1_x + "|"+ enemy_1_y + "|" + enemy_2_x + "|" + enemy_2_y + "|" + enemy_3_x + "|" + enemy_3_y + "|" + enemy_4_x + "|" + enemy_4_y);
			check=true;
		}
		else {
			//System.out.println("false");
			check=false;
		}
		//
	}
}
