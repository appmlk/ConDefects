# ansmod = 10 ** 9 + 7
# ansmod = 998244353

import sys


def main(f):
    s = list(map(int, f.readline()[:-1]))

    if len(s) == 1:
        if s[0] == 0:
            return 0
        else:
            return 1

    ans = 0
    f = False
    for c in s:
        if c == 0:
            if f:
                ans += 1
                f = False
            else:
                f = True
        else:
            if f:
                ans += 1
                f = False
            ans += 1

    return ans


if __name__ == "__main__":
    print(main(sys.stdin))
    # print(main(sys.stdin) % ansmod)
