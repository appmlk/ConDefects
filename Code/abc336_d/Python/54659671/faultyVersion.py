import sys

sys.setrecursionlimit(10**7)
read_int = lambda: int(sys.stdin.readline())
read_ints = lambda: list(map(int, sys.stdin.readline().split(" ")))
read_float = lambda: float(sys.stdin.readline())
read_floats = lambda: list(map(float, sys.stdin.readline().split(" ")))


def get_logger(debug=True):
    if not debug:
        return type("Dummy", (object,), {"debug": lambda self, a: None})()
    import logging

    logger = logging.getLogger("")
    logger.setLevel(logging.DEBUG)
    handler = logging.StreamHandler(sys.stdout)
    handler.setFormatter(logging.Formatter("[%(funcName)s:%(lineno)s] %(message)s"))
    logger.addHandler(handler)
    return logger


# -------------------------------
log = get_logger(False)

N = read_int()
a = read_ints()

ans = 0
a[0], a[N - 1] = 1, 1
for i in range(1, N):
    if a[i] > a[i - 1]:
        a[i] = a[i - 1] + 1
for i in range(N - 2, -1, -1):
    if a[i] > a[i + 1]:
        a[i] = a[i + 1] + 1
    ans = max(ans, a[i])
print(ans)
