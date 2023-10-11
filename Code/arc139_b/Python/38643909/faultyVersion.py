# +-----------+--------------------------------------------------------------+
# |   main    |                                                              |
# +-----------+--------------------------------------------------------------+
def main():
    T = int(input())
    for _ in range(T):
        print(solve(*map(int, input().split())))
    return

def solve(n, a, b, x, y, z):
    y = min(y, x*a)
    z = min(z, x*b)
    if z*a < y*b:
        (a, y), (b, z) = (b, z), (a, y)
    ans = 1<<61
    if a**2 < n:
        for p in range(n//a + 1):
            q, r = divmod(n - a*p, b)
            cost = y*p + z*q + x*r
            ans = min(ans, cost)
    else:
        for q in range(a):
            p, r = divmod(n - b*q, a)
            cost = y*p + z*q + x*r
            ans = min(ans, cost)
    return ans




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