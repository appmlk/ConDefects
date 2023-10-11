n, x, y = map(int, input().split())
a_list = list(map(int, input().split()))

for i in range(n):
    a_list[i] %= x+y

flag = True
for i in range(n):
    if a_list[i] // x == 0 and a_list[i] // y > 0:
        print("Second")
        exit()
    elif a_list[i] // x > 0 and a_list[i] // y == 0:
        print("First")
        exit()
    elif a_list[i] // x > 0:
        flag = False

if flag:
    print("Second")
else:
    print("First")