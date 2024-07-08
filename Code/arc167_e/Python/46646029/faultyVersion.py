def One_Square_in_a_Triangle():
     S = int(input())
     if S in [1, 2, 3, 5, 7]:
         return "NO"
     if S == 4:
         return "YES" + "\n" + "1 " + "1 " + "1 " + "3 " + "3 " + "3"
     else:
         a = S // 2
         if S % 2 == 0:
             return "YES" + "\n" + "0 " + "0 " + str(a) + " " + str(a) + " 0 "+ str(a)
         else:
             return "YES" + "\n" + "0 " + "0 " + str(a) + " " + str(a-1) + " " + str(a-1) + " " +str(a-4)

T = int(input())
for t in range(1, T+1):
    print(str(One_Square_in_a_Triangle()))