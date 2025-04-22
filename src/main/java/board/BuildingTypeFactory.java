package board;

public class BuildingTypeFactory {
    public BuildingType build(BuildingCode code) {
        switch (code) {
            case SETTLEMENT:
                return new SettlementBuildingType();
            case CITY:
                return new CityBuildingType();
            case FORT:
                return null;
            case TEMPLE:
                return null;
            case OBSERVATORY:
                return null;
        }
        return null;
    }
}
