n = int(input())
for i in range(n):
  l = list(map(int,input().split()))
  if len(set(l)) <= 2:
    if l.count(l[0]) != 1:
      print(l[0])
    elif l.count(l[1]) != 1:
      print(l[1])
  else:
    lm = [min(l[0],l[1]),min(l[0],l[2]),min(l[2],l[1])]
    ls = [abs(l[0]-l[1]),abs(l[0]-l[2]),abs(l[2]-l[1])]
    lp = [i%3 for i in ls]
    if 0 not in lp:
      print(-1)
    else:
      ans = []
      for i in range(3):
        if lp[i] != 0:
          continue
        t = 2 - i
        ans.append(ls[i]+lm[i])
      if ans == []:
        print(-1)
      else:
        print(min(ans))