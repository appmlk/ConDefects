l = list(map(int, input().split()))
l.sort()
if(l[0] > l[1] + l[2]): print("-1")
else: print(l[2])