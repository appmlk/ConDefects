X,Y = map(int, input().split())
def di(a,b,mod): #b/a ax≡b(mod mod)
    d=pow(a,-1,mod)
    return b*d%mod

if X==0:
    if abs(Y)==1 : print("2 0")
    elif abs(Y)==2 : print("1 0")
    else : print(-1)
elif Y==0:
    if abs(X)==1 : print("0 2")
    elif abs(X)==2 : print("0 1")
    else : print(-1)
else:
    import math
    g=math.gcd(X,Y)
    if 2<g : print(-1)
    else:
        X//=g ; Y//=g
        ansx=di(Y,2//g,X)
        ansy=(ansx*Y-2)//X
        print(ansx,ansy)