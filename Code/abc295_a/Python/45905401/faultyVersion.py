n = int(input())
a = list(input().split())

ans = "No"
for i in a:
    if i == "and" or i == "not" or i == "that" or i == "you":
        print("Yes")
        exit()

print(ans)