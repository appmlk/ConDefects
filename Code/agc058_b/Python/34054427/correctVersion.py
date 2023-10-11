import sys
from functools import lru_cache
sys.setrecursionlimit(50000)


def input_general():
    return sys.stdin.readline().rstrip('\r\n')


def input_num():
    return int(sys.stdin.readline().rstrip("\r\n"))


def input_multi(x=int):
    return map(x, sys.stdin.readline().rstrip("\r\n").split())


def input_list(x=int):
    return list(input_multi(x))


def main():
    n = input_num()
    arr = input_list()
    P = 998244353

    arr = [x - 1 for x in arr]

    rev = [-1] * n
    for i, x in enumerate(arr):
        rev[x] = i

    def get_bound(arr):
        right = list(range(n))

        stack = []
        for i, x in enumerate(arr):
            while stack and stack[-1][1] < x:
                j, y = stack.pop()
                right[j] = i - 1

            stack.append((i, x))

        for i, x in stack:
            right[i] = n - 1
        return right

    right = get_bound(arr)
    left = get_bound(arr[::-1])[::-1]
    left = [n - 1 - x for x in left]
    dp = [0] + [1] * (n + 1)
    # print([], dp)
    for i, x in enumerate(arr):

        l, r = left[i], right[i]
        addon = [0] + [0] * n  # When we are actually adding some x into the list
        for idx in range(l, r + 1):
            addon[idx + 1] = dp[idx + 1] - dp[l]

        psum = [0]
        for x in addon:
            psum.append(psum[-1] + x)

        dp = [(x + y) % P for x, y in zip(dp, psum)]
        # print(addon, dp)

    print((dp[-1] - dp[-2]) % P)


if __name__ == "__main__":
    main()