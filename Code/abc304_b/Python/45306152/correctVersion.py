n = int(input())

for i in range(7):
    if n <= 10**(3 + i) - 1:
        print((n // (10**i)) * 10**i)
        break
