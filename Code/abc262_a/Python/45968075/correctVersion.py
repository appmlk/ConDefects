a = int(input())
# a, b = map(int, input().split())

if a % 4 == 2:
    print(a)
elif a % 4 == 1:
    print(a + 1)
elif a % 4 == 3:
    print(a + 3)
elif a % 4 == 0:
    print(a + 2)