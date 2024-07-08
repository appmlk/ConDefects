import pandas as pd
n, m = map(int, input().split())
aim_am_lis = list(map(int, input().split()))
am_lis = []
for i in range(n):
    am_lis.append(list(map(int, input().split())))
df = pd.DataFrame(data=am_lis).sum()
cnt=0
for i in range(m):
    if df[i] > aim_am_lis[i]:
        cnt+=1

if cnt==m:
    print("Yes")
else:
    print("No")


