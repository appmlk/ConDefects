S = input()
state = 0
for i in S:
    if state == 0 and i == 'B':
        state += 1
    elif state == 1 and i == 'C':
        state += 1
    
    if state == 0 and i != 'A':
        print("No")
        exit()
    elif state == 1 and i != 'B':
        print("No")
        exit()
    elif state == 2 and i != 'C':
        print("No")
        exit()
print("Yes")