N = int(input())

people = [list(map(int, input().split())) for _ in range(N)]     

S = input() 

r_hash = {}
l_hash = {}
flag = False
for i, (xy, moji)in enumerate(zip(people, S)):
          # print(i, xy, moji)
          x = xy[0]
          y = xy[1]
          
          if moji == 'R':
                    if l_hash.get(y, 0) > x:
                              flag = True
                              break
                    elif r_hash.get(y, float('inf')) > x:
                              r_hash[y] = x
          else:
                    if r_hash.get(y, float('inf')) < x:
                              flag = True
                              break
                    elif l_hash.get(y, 0) < x:
                              r_hash[y] = x
                    
if flag:
          print("Yes")
else:
          print("No")