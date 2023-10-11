import sys
readline = sys.stdin.readline

#n = int(readline())
#*a, = map(int,readline().split())
# b = [list(map(int,readline().split())) for _ in range()]

n,a,b,c,d = map(int,readline().split())

def check(a,b,c,d):
    #XY
    if abs(b-c)==1: return 1
    #YY,XX
    if b == c:
        if b==0:
            if a==0 or d==0: return 1
        else:
            return 1

print("Yes" if check(a,b,c,d) else "No")