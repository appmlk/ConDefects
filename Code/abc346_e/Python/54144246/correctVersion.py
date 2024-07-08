import sys
stdin = sys.stdin.readline
stdout = sys.stdout.write

MOD = 10 ** 9 + 7
INF = float("inf")


def next_int(): return int(stdin().strip())
def next_float(): return float(stdin().strip())
def next_array(): return list(map(int, stdin().split()))
def next_string(): return stdin().strip()


def solve():
    h, w, m = next_array()
    count = [0] * 200001
    count[0] = h * w
    ops = []
    for i in range(m):
        t, a, x = next_array()
        ops.append([t, a-1, x])

    r = h
    c = w
    row_visited = [False] * h
    col_visited = [False] * w
    for i in range(m - 1, -1, -1):
        t, a, x = ops[i]
        if t == 1:
            if not row_visited[a]:
                row_visited[a] = True
                count[0] -= c
                count[x] += c
                r -= 1
        else:
            if not col_visited[a]:
                col_visited[a] = True
                count[0] -= r
                count[x] += r
                c -= 1
    res = []
    for i in range(200001):
        if count[i] > 0:
            res.append([str(i), str(count[i])])
    print(len(res))
    for p in res:
        print(" ".join(p))


if __name__ == "__main__":
    total_num_tests = 1
    for test_index in range(0, total_num_tests):
        solve()
