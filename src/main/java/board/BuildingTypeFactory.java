package board;

public class BuildingTypeFactory {
    public BuildingType build(BuildingCode code) {
        switch (code) {
            case SETTLEMENT:
                return new SettlementBuildingType();
            case CITY:
                return new CityBuildingType();
            case FORT:
                return new FortBuildingType();
            case TEMPLE:
                return new TempleBuildingType();
            case OBSERVATORY:
                return new ObservatoryBuildingType();
        }
        return null;
    }
}
