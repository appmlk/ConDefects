import sys


def resolve():
    input = sys.stdin.readline
    memo = {}

    def q(i, j, k):
        if i > j:
            i, j = j, i
        t = (i, j, k)
        if t in memo:
            return memo[t]
        if i == k or j == k:
            return True
        print("?", i, j, k, flush=True)
        memo[t] = input().rstrip() == "Yes"
        return memo[t]

    n = int(input())
    x = 1
    for i in range(2, n + 1):
        if not q(i, i, x):
            x = i
    from collections import deque

    queue = deque(deque([x]) for x in range(1, n + 1))
    while len(queue) > 1:
        # print(queue)
        l = queue.popleft()
        r = queue.popleft()
        a = deque()
        li = l.popleft()
        ri = r.popleft()
        while True:
            if q(li, x, ri):
                a.append(ri)
                if not r:
                    a.append(li)
                    break
                ri = r.popleft()
            else:
                a.append(li)
                if not l:
                    a.append(ri)
                    break
                li = l.popleft()
        queue.append(a + l + r)
    ans = [None] * n
    for i, j in enumerate(queue[0], start=1):
        ans[j - 1] = i
    print("!", *ans, flush=True)


if __name__ == "__main__":
    resolve()
