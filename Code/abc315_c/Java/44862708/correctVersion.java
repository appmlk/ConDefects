import java.util.*; import java.io.*; import java.math.*;
public class Main{
	//見なくていいよ　ここから------------------------------------------
	static class InputIterator{
		ArrayList<String> inputLine = new ArrayList<>(1024);
		int index = 0; int max; String read;
		InputIterator(){
			try{
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
				while((read = br.readLine()) != null){
					inputLine.addAll(Arrays.asList(read.split(" ")));
				}
			}catch(IOException e){}
			max = inputLine.size();
		}
		boolean hasNext(){return (index < max);}
		String next(){
			if(hasNext()){
				return inputLine.get(index++);
			}else{
				throw new IndexOutOfBoundsException("There is no more input");
			}
		}
	}
	static HashMap<Integer, String> CONVSTR = new HashMap<>();
	static InputIterator ii = new InputIterator();//This class cannot be used in reactive problem.
	static PrintWriter out = new PrintWriter(System.out);
	static void flush(){out.flush();}
	static void myout(Object t){out.println(t);}
	static void myerr(Object... t){System.err.print("debug:");System.err.println(Arrays.deepToString(t));}
	static String next(){return ii.next();}
	static boolean hasNext(){return ii.hasNext();}
	static int nextInt(){return Integer.parseInt(next());}
	static long nextLong(){return Long.parseLong(next());}
	static double nextDouble(){return Double.parseDouble(next());}
	static ArrayList<String> nextCharArray(){return myconv(next(), 0);}
	static ArrayList<String> nextStrArray(int size){
		ArrayList<String> ret = new ArrayList<>(size);
		for(int i = 0; i < size; i++){
			ret.add(next());
		}
		return ret;
	}
	static ArrayList<Integer> nextIntArray(int size){
		ArrayList<Integer> ret = new ArrayList<>(size);
		for(int i = 0; i < size; i++){
			ret.add(Integer.parseInt(next()));
		}
		return ret;
	}
	static ArrayList<Long> nextLongArray(int size){
		ArrayList<Long> ret = new ArrayList<>(size);
		for(int i = 0; i < size; i++){
			ret.add(Long.parseLong(next()));
		}
		return ret;
	}
	@SuppressWarnings("unchecked")
	static String myconv(Object list, int no){//only join
		StringBuilder sb = new StringBuilder("");
		String joinString = CONVSTR.get(no);
		if(list instanceof String[]){
			return String.join(joinString, (String[])list);
		}else if(list instanceof long[]){
			long[] tmp = (long[])list;
			if(tmp.length == 0){
				return "";
			}
			sb.append(String.valueOf(tmp[0]));
			for(int i = 1; i < tmp.length; i++){
				sb.append(joinString).append(String.valueOf(tmp[i]));
			}
			return sb.toString();
		}else if(list instanceof int[]){
			int[] tmp = (int[])list;
			if(tmp.length == 0){
				return "";
			}
			sb.append(String.valueOf(tmp[0]));
			for(int i = 1; i < tmp.length; i++){
				sb.append(joinString).append(String.valueOf(tmp[i]));
			}
			return sb.toString();
		}else if(list instanceof ArrayList){
			ArrayList tmp = (ArrayList)list;
			if(tmp.size() == 0){
				return "";
			}
			sb.append(tmp.get(0));
			for(int i = 1; i < tmp.size(); i++){
				sb.append(joinString).append(tmp.get(i));
			}
			return sb.toString();
		}else{
			throw new ClassCastException("Don't join");
		}
	}
	static ArrayList<String> myconv(String str, int no){//only split
		String splitString = CONVSTR.get(no);
		return new ArrayList<String>(Arrays.asList(str.split(splitString)));
	}
	static ArrayList<String> myconv(String str, String no){
		return new ArrayList<String>(Arrays.asList(str.split(no)));
	}
	public static void main(String[] args){
		CONVSTR.put(8, " "); CONVSTR.put(9, "\n"); CONVSTR.put(0, "");
		solve();flush();
	}
	//見なくていいよ　ここまで------------------------------------------
	//このコードをコンパイルするときは、「-encoding UTF-8」を指定すること
	static void solve(){//ここがメイン関数
		int N = nextInt();
		HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
		for(int i = 0; i < N; i++){
			int F = nextInt();
			int S = nextInt();
			if(!map.containsKey(F)){
				map.put(F, new ArrayList<Integer>());
			}
			map.get(F).add(S);
		}
		int output = 0;
		ArrayList<Integer> maxes = new ArrayList<>();
		for(int key : map.keySet()){
			ArrayList<Integer> list = map.get(key);
			Collections.sort(list, Comparator.reverseOrder());
			maxes.add(list.get(0));
			if(list.size() > 1){
				output = Math.max(output, list.get(0) + (list.get(1) / 2));
			}
		}
		Collections.sort(maxes, Comparator.reverseOrder());
		if(maxes.size() > 1){
			output = Math.max(output, maxes.get(0) + maxes.get(1));
		}
		myout(output);
	}
	//メソッド追加エリア　ここから



	//メソッド追加エリア　ここまで
}