
input = [list(map(int, input().split())) for i in range(2)]

BuildingNum = input[0]
HeightList = input[1]


print(HeightList)

Height_FirstBuld = HeightList[0]
found = 0

for i in range(len(HeightList)):
    if HeightList[i] > Height_FirstBuld:
        print(i+1)
        found = 1
        break

if(found == 0):
    print(-1)

