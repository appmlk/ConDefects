import sys
from array import array

input = lambda: sys.stdin.buffer.readline().decode().rstrip()
inp = lambda dtype: [dtype(x) for x in input().split()]
debug = lambda *x: print(*x, file=sys.stderr)
sum_n = lambda n: (n * (n + 1)) // 2
get_bit = lambda x, i: (x >> i) & 1
get_col = lambda arr, i: [row[i] for row in arr]
ceil_ = lambda a, b: a // b if (a >= 0) ^ (b > 0) else (abs(a) + abs(b) - 1) // abs(b)
Mint, Mlong, out = 2 ** 30 - 1, 2 ** 62 - 1, []
dx = array('b', [0, 1, 0, -1, 1, -1, 1, -1])
dy = array('b', [1, 0, -1, 0, 1, -1, -1, 1])

for _ in range(1):
    # interactive
    def ask(q) -> int:
        sys.stdout.write(q)
        sys.stdout.write('\n')
        sys.stdout.flush()
        ret = int(input())
        if ret == -1: exit()
        return ret


    def answer(q):
        sys.stdout.write(q)
        sys.stdout.write('\n')
        sys.stdout.flush()


    n = int(input())
    lg = len(bin(n - 1)[2:])
    answer(f'{lg}')

    for i in range(lg):
        tem = []
        for j in range(n):
            if get_bit(j, i): tem.append(j + 1)
        answer(' '.join(map(str, [len(tem)] + tem)))

    s = input()[::-1]
    answer(str(int(s, 2) + 1))

# print('\n'.join(map(str, out)))
