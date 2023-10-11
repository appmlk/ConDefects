# ansmod = 10 ** 9 + 7
# ansmod = 998244353

import sys


def main(f):
    s = f.readline()[:-1]
    t = f.readline()[:-1]

    for id, i in enumerate(s):
        if i != t[id]:
            return id + 1

    return id + 2


if __name__ == "__main__":
    print(main(sys.stdin))
    # print(main(sys.stdin) % ansmod)
