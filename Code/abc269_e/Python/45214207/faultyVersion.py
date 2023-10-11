# +-----------+--------------------------------------------------------------+
# |   main    |                                                              |
# +-----------+--------------------------------------------------------------+
def main():
    global N
    N = int(input())
    x = query(0, N, axis=0)
    x += 1
    y = query(0, N, axis=1)
    y += 1
    print(x, y)
    return


def query(lo, hi, axis):
    while hi - lo > 1:
        mi = (lo + hi) >> 1
        abcd = [0, N, 0, N]
        abcd[axis*2:axis*2+2] = [lo, mi]
        abcd[0] += 1  # 1-index3
        abcd[2] += 1  # 1-index
        print('?', *abcd, flush=True)

        T = int(input())
        if T == mi-lo:
            lo = mi
        else:
            hi = mi
    return lo




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
        # __file = open('./input.txt')
        # input = lambda: __file.readline().rstrip()
        __debug = True
    except:
        __debug = False
    main()