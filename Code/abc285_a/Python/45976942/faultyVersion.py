# a = int(input())
# b = input()
a, b = map(int, input().split())
# l = list(map(int, input().split()))
# l = [input() for _ in range(a)]
# l = [list(map(int, input().split())) for _ in range(a)]

if b % 2 <= 1:
    print("Yes")
else:
    print("No")
