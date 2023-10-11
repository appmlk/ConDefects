N = int(input())

n = N + 100000 - 1
a, b, c, d, e, f = str(n)

P = f"{a}{a}{b}{c}{d}{d}{e}{f}{e}"
print(P)
