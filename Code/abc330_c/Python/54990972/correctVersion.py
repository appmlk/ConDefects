import math
def rint(offset=0,base=10): return list(map(lambda x: int(x, base)+offset, input().split())) 
def full(s, f=int, *args): return [full(s[1:], f) if len(s) > 1 else f(*args) for _ in range(s[0])]
def shift(*args,offset=-1): return (a+offset for a in args)

D, = rint()

D2 = math.floor(math.sqrt(D))
ans = D
for x in range(D2+1):
    y =  math.floor(math.sqrt(D-x*x))

    ans = min(ans, abs(x*x+y*y-D))
    y+=1
    ans = min(ans, abs(x*x+y*y-D))

print(ans)