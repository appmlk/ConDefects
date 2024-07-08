n = input()

for i in range(len(n)-1):
    if int(n[i]) < int(n[i+1]):
        print("No")
        break
    
else:
     print("Yes")    
