# +-----------+--------------------------------------------------------------+
# |   main    |                                                              |
# +-----------+--------------------------------------------------------------+
def main():
    XA, YA, XB, YB, XC, YC = map(int, input().split())
    XB -= XA
    XC -= XA
    XA = 0
    YB -= YA
    YC -= YA
    YA = 0
    if XC < XB:
        XC *= -1
        XB *= -1
    if YC < YB:
        YC *= -1
        YB *= -1
    
    __print(XB, YB, XC, YC, XB < XC, YB < YC)
    ans = abs(XC - XB) + abs(YC - YB) + abs(XB - XA) + abs(YB - YA) - 1
    if XB < 0:
        if XB < XC:
            ans += 2
        if YB == 0:
            ans += 2
        elif YB < 0 and YB < YC:
            ans += 2
    elif XB == XA:
        if YB < 0 and YB < YC:
            ans += 4
        elif XB < XC:
            ans += 2
    else:
        # ここに問題がある
        if YB == YC:
            pass
        elif YB < YC:
            if XB == XC:
                if YB == 0:
                    ans += 2
            else:
                ans += 2
    print(ans)



# +-----------+--------------------------------------------------------------+
# |  library  | See Also : https://github.com/nodashin6/atcoder              |
# +-----------+--------------------------------------------------------------+





# +-----------+--------------------------------------------------------------+
# |   other   |                                                              |
# +-----------+--------------------------------------------------------------+
import sys
input = lambda: sys.stdin.readline().rstrip()
__print = lambda *args, **kwargs: print(*args, **kwargs) if __debug else None


if __name__ == '__main__':
    # for test on local PC
    try:
        __file = open('./input.txt')
        input = lambda: __file.readline().rstrip()
        __debug = True
    except:
        __debug = False
    main()