import heapq
n=int(input())
x=[-10**18]+list(map(int,input().split()))+[10**18]
y=[0]+list(map(int,input().split()))+[0]
l_pool=[]
r_pool=[]
min_diff=10**18
for i in range(1,n+2):
  if y[i]-y[i-1]==0:
    continue
  elif y[i]-y[i-1]>0:
    l_cur=x[i-1]+1
    l_cnt=y[i]-y[i-1]
    heapq.heappush(l_pool,(l_cur,l_cnt))
  elif y[i]-y[i-1]<0:
    r_cur=x[i]-1
    r_cnt=y[i-1]-y[i]
    while r_cnt!=0:
      l_cur,l_cnt=heapq.heappop(l_pool)
      diff_cur=r_cur-l_cur
      min_diff=min(min_diff,diff_cur)
      if r_cnt>=l_cnt:
        r_cnt-=l_cnt
      elif r_cnt<l_cnt:
        l_cnt-=r_cnt
        r_cnt=0
        heapq.heappush(l_pool,(l_cur,l_cnt))
if min_diff>=10**15:
  print(-1)
else:
  print(min_diff)