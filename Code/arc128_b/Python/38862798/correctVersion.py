T = int(input())
INF = float("inf")
def check(a,b,c):
  if abs(a-b) % 3 == 0:
      return max(a,b)
  return INF

for t in range(T):
  R, G, B = map(int, input().split())
  ans = []
  if R==G or G==B or B==R:
    rgb = sorted([R,G,B])
    print(rgb[1])
  else:
    ans.append( check(R, G, B) )
    ans.append( check(R, B, G) )
    ans.append( check(B, G, R) )
    #print(ans)
    print(-1) if min(ans)==INF else print(min(ans))