h,w,a,b = map(int,input().split())
def f(h,w,a,b):
  if a > b:
    return [(y,x) for x,y in f(w,h,b,a)]
  if h == 1:
    return [(0,y) for y in range(w)]
  if h == 2 and (a,b) == (1,1):
    return [(0,0),(1,0)]+[(x,y+1) for x,y in f(2,w-1,1,0)]
  return [(x,0) for x in range(h)]+[(h-1-x,y+1) for x,y in f(h,w-1,h-1-a,b-1)]
for x,y in f(h,w,a-1,b-1):
  print(x+1,y+1)