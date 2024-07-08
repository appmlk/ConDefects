inf = 10**12

def calc_1(x,y):
    if y == 0 and x >= 0:
        return x
    return inf

def calc_2(x,y):
    if x == y and x >= 0:
        return x
    return inf

def calc_12(x,y):
    if y >= 0 and x >= y:
        return x
    return inf

def calc_13(x,y):
    if x >= 0 and y >= 0:
        return x+y
    return inf

def calc_14(x,y):
    if y >= 0 and x >= -y:
        return y + (x + y)
    return inf

def calc_24(x,y):
    if y >= 0 and -y <= x <= y and (y-x) % 2 == 0:
        return y
    return inf

def calc_124(x,y):
    res = min(calc_12(x,y), calc_14(x,y), calc_24(x,y),
                1 + calc_24(x-1,y))
    return res

def calc_136(x,y):
    res = min(calc_13(x,y), calc_14(x,-y), calc_14(y,-x))
    return res

def calc_146(x,y):
    res = min(calc_14(x,y), calc_14(x,-y), calc_24(y,-x),
                1 + calc_24(y,-(x-1)))
    return res

def calc_zzz(x,y,s):
    res = inf
    if s[0] == '1':
        res = min(res, calc_1(x,y))
        if s[1] == '1':
            res = min(res, calc_12(x,y))
            if s[3] == '1':
                res = min(res, calc_124(x,y))
        if s[2] == '1':
            res = min(res, calc_13(x,y))
            if s[5] == '1':
                res = min(res, calc_136(x,y))
        if s[3] == '1':
            res = min(res, calc_14(x,y))
            if s[5] == '1':
                res = min(res, calc_146(x,y))
    if s[1] == '1':
        res = min(res, calc_2(x,y))
        if s[3] == '1':
            res = min(res, calc_24(x,y))
    return res

def rev_s(s):
    res = [s]
    res.append( s[0] + s[7] + s[6] + s[5] + s[4] + s[3] + s[2] + s[1])
    res.append( s[4] + s[3] + s[2] + s[1] + s[0] + s[7] + s[6] + s[5])
    res.append( s[4] + s[5] + s[6] + s[7] + s[0] + s[1] + s[2] + s[3])
    return res

def calc(x,y,s):
    res = inf
    for _ in range(4):
        for mul_x,mul_y,si in zip([1,1,-1,-1], [1,-1,1,-1], rev_s(s)):
            res = min(res, calc_zzz(x*mul_x, y*mul_y, si))
        x,y = y, -x
        s = s[2:] + s[:2]
    
    if res == inf:
        res = -1
    return res

t = int(input())
ans = []
for _ in range(t):
    x,y,s = input().split()
    x = int(x)
    y = int(y)
    ans.append(calc(x,y,s))

print(*ans, sep='\n')