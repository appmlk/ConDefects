n = int(input())

one = ["H", "D", "C", "S"]
two = ["A", "2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K"]

spawned = []

for i in range(n):
    s = input()

    if not s[0] in one or not s[1] in two or s in spawned:
        print("No")
        exit(0)

    else:
        spawned.append(s)

print("Yes")