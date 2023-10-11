N = int(input())
M = 2 * N
xstr = str(((N - 1) % 4) + 1) + "4" * ((N - 1) // 4)
print(M)
print(xstr)