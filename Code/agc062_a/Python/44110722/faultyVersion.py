t = int(input())
for _ in range(t):
    n = int(input())
    c = input()
    i = 0
    j = n - 1
    while i < n and c[i] == 'A':
        i += 1
    while j > 0 and c[j] == 'B':
        j -= 1
    if i - j == 1 and c[-1] == 'B':
        print('B')
    else:
        print('A')