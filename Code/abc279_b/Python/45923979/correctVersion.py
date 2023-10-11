a = list(input())
b = list(input())

n = len(a)
m = len(b)

if n < m:
    print('No')
elif n == m:
    print('Yes' if a == b else 'No')
else:
    for i in range(n-m+1):
        t = a[i:i+m]
        if t == b:
            print('Yes')
            break
    else:
        print('No')
