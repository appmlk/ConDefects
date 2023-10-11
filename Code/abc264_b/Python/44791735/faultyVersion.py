R,C = map(int,input().split())
t = min(R,C)
ans = 'white'
if t % 2 == 1:
  ans = 'black'
print(ans)