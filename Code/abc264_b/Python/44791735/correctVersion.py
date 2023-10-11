R,C = map(int,input().split())
t = min(R,C,16-R,16-C)
ans = 'white'
if t % 2 == 1:
  ans = 'black'
print(ans)