import math
A,B = map(int,input().split())
if A > B:
    A,B = B,A


def isOK(key):
    if math.cos(key)*A/math.cos(math.pi/6-key) <= B:
        return True
    else:
        return False


def search():
    l,r = 0,math.pi/12
    while r-l > 10**(-15):
        mid = (r+l)/2
        if isOK(mid):
            r = mid
        else:
            l = mid
    return A/math.cos(math.pi/6-r)


print(search())