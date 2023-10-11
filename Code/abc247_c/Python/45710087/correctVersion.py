li = []
def write(n):
    if n == 1:
        return [1]
    else:
        return write(n-1) + [n] + write(n-1)
n = int(input())
print(*write(n))