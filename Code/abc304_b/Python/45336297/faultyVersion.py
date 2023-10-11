n = int(input())
if n <= 10**3-1:
    print(n)
elif n <= 10**4-1:
    print(n - n%10)
elif n <= 10**5-1:
    print(n - n%100)
elif n <= 10**6-1:
    print(n - n%1000)
elif n <= 10**7-1:
    print(n - n%10000)
elif n <= 10**8-1:
    print(n - n%100000)